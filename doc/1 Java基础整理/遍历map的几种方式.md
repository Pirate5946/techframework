https://blog.csdn.net/zajiayouzai/article/details/80922610

### 先创建一个map，添加好数据：
```
Map<String, String> map = new HashMap<>();
for (int i = 0; i < 1000000; i++) {
    map.put(i + "", i + "AA");
}
```

### keySet的for循环方式：
```
//只获取key
public static void keySetForGetKey(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    for (String key : map.keySet()) {
    }
    long endTime = System.currentTimeMillis();
    System.out.println("keySetForGetKey运行时间" + (endTime - startTime));
}
//获取key和value
public static void keySetForGetKeyAndValue(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    for (String key : map.keySet()) {
        String value = map.get(key);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("keySetForGetKeyAndValue运行时间" + (endTime - startTime));
}
```

### 2、keySet的iterator迭代器方式：
 ```   
///只获取key
public static void keySetIteratorGetKey(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
        String key = iterator.next();
    }
    long endTime = System.currentTimeMillis();
    System.out.println("keySetIteratorGetKey运行时间" + (endTime - startTime));
}
//获取key和value
public static void keySetIteratorGetKeyAndValue(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
        String key = iterator.next();
        String value = map.get(iterator.next());
    }
    long endTime = System.currentTimeMillis();
    System.out.println("keySetIteratorGetKeyAndValue运行时间" + (endTime - startTime));
}
```

### 3、entrySet的for循环方式：
```
//只获取key
public static void entrySetForGetKey(Map<String, String> map){
  long startTime = System.currentTimeMillis();
  for (Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
  }
  long endTime = System.currentTimeMillis();
  System.out.println("entrySetForGetKey运行时间" + (endTime - startTime));
}
//获取key和value
public static void entrySetForGetKeyAndValue(Map<String, String> map){
  long startTime = System.currentTimeMillis();
  for (Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
  }
  long endTime = System.currentTimeMillis();
  System.out.println("entrySetForGetKeyAndValue运行时间" + (endTime - startTime));
}
```

### 4、entrySet的iterator迭代器方式：
```

//只获取key
public static void entrySetIteratorGetKey(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
        String key = iterator.next().getKey();
    }
    long endTime = System.currentTimeMillis();
    System.out.println("entrySetIteratorGetKey运行时间" + (endTime - startTime));
}
//获取key和value
public static void entrySetIteratorGetKeyAndValue(Map<String, String> map){
    long startTime = System.currentTimeMillis();
    Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
        String key = iterator.next().getKey();
        String value = iterator.next().getValue();
    }
    long endTime = System.currentTimeMillis();
    System.out.println("entrySetIteratorGetKeyAndValue运行时间" + (endTime - startTime));
}
```

总结：

entrySet的方式整体都是比keySet方式要高一些；

单纯的获取key来说，两者的差别并不大，但是如果要获取value，还是entrySet的效率会更好，因为keySet需要从map中再次根据key获取value，而entrySet一次都全部获取出来；

**iterator的迭代器方式比foreach的效率高**

## 二、foreach和iterator
其实foreach的语法只是对iterator进行了简单的包装，使用起来更加方便而已，

但是如果在foreach循环体内，对集合元素进行删除添加操作的时候，会报出ConcurrentModificationException，并发修改异常。

如果需要在遍历集合的时候对象集合中元素进行删除操作，需要使用iterator的遍历方式，iterator自带的remove删除方式不会报出异常。

# 试验dubbo——负载均衡

## 试验现象1

若```consumer```端设置```gropu="*"```，确实会出现交易只发到一个```provider```上的问题  
见```com.alibaba.dubbo.rpc.cluster.support.MergeableClusterInvoker```的```invoke```方法，有如下片段：
```
for (final Invoker<T> invoker : invokers) {
    if (invoker.isAvailable()) {
        return invoker.invoke(invocation);
    }
}
```

## 试验现象2

后续发现问题更复杂一些，看```provider```有几个```group```：若只有一个```group```，就是前述情况；若多个，还是会被包装成```MockClusterInvoker```。
见```com.alibaba.dubbo.registry.integration.RegistryDirectory```的```toMergeMethodInvokerMap```方法，有如下片段：
```
if (groupMap.size() == 1) {
    result.put(method, groupMap.values().iterator().next());
} else if (groupMap.size() > 1) {
    List<Invoker<T>> groupInvokers = new ArrayList<Invoker<T>>();
    for (List<Invoker<T>> groupList : groupMap.values()) {
        groupInvokers.add(cluster.join(new StaticDirectory<T>(groupList)));
    }
    result.put(method, groupInvokers);
}
```

# 总结

```group=*```且未设置```merger```的情况下，总会用```List<Invoker>```第一个。
```
if (ConfigUtils.isEmpty(merger)) { // If a method doesn't have a merger, only invoke one Group
    //试验现象1代码片段
}
```
当```provider```有1个```group```，```List<Invoker>```元素类型为```RegistryDirectory$InvokerDelegate```，即直接到```provider```;  
当有多个```group```，元素类型为```MockClusterInvoker```，即会经过```LB```

另，还有一个问题，这么分析下来，可见，总是会调用那一个```group```
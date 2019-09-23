#试验dubbo——负载均衡

经试验，若```consumer```端设置```gropu="*"```，确实会出现交易只发到一个```provider```上的问题  
见```com.alibaba.dubbo.rpc.cluster.support.MergeableClusterInvoker```的```invoke```方法，有如下片段：
```
for (final Invoker<T> invoker : invokers) {
    if (invoker.isAvailable()) {
        return invoker.invoke(invocation);
    }
}
```
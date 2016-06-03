添加一个API的流程：

1.在pojo包添加pojo
2.在Service包添加pojo操作类
    -操作类需要使用单例模式
3.在Business包中添加处理请求的类和方法:
    -类需要继承BasicBusiness并实现参数为request和response的构造函数。
    -方法无参数，返回值为Object
    -获取Service操作时要获取单例
4.在APIPathMapping添加对应的请求配置

# excel-toData
这是一个利用xml制定excel变成我们想要的对应的项目
1.关联jar(poi-3.14-20160307.jar、
poi-ooxml-3.14-20160307.jar、
poi-ooxml-schemas-3.14-20160307.jar 、
xmlbeans-2.6.0.jar和JAXB)

2.用JAXB技术由bean生成对应的xml，由xml制定规则让excel生成我们想要的对象
   excle——>xml——>pojo
3.关于dome，因为采用了sqlite数据库，所以在用的时候要修改数据库地址。
dome数据也放在项目中。
(1)生成xml地址
localhost:8080/excel_test/makeXmlServlet
(2)上传excel
localhost:8080/excel_test/writeServlet
## 本项目采用Apache 2.0协议开源

License

Copyright (c) 2016 liuchanghao

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
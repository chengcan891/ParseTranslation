1.项目是IDEA创建的

2.项目用于Android多语言翻译对比，结果输出到exportResult.html

3.使用方法

3.1修改Main,指定翻译的标准文件夹，我的是Android资源文件的values

3.2修改Main,指定需要对比的翻译文件,我的是Android资源文件的values-en

3.3运行Main,则生成exportResult.html文件

4.exportResult.html文件结构

文件统计：统计values-en中比values少了那些资源文件
翻译统计：统计values-en和values中同名文件中string的翻译是否正确，这里的正确并不是真正的翻译，而是校验字符串中的格式化字符是否一致，这里的格式化字符是String.format对应的格式字符

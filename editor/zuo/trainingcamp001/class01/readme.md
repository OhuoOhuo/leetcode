滑动窗口与单调栈的使用场景：



想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程（不回退！！）

想用滑动窗口最值的更新结构，就看看处理流程下，是否需要最值这个信息

-------------------------------------------------------
一种特别设计的栈结构，为了解决如下的问题：

给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。


想用单调栈，要想办法把具体的问题转化为单调栈所解决的原问题

滑动窗口及其最大值和最小值的更新结构、单调栈，都是重要算法原型

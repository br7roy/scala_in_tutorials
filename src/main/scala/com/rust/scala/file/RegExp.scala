package com.rust.scala.file

/**
  * @author Rust
  */
object RegExp {
  def main(args: Array[String]): Unit = {

    val methodNameRegexStr = "\\s*(?:\\w+\\s+)?\\w+<?\\w+>?\\s+(\\w+)"
    val singleParamRegexStr = "[^,]*\\w+<?\\w+>?\\s+(\\w+)\\s*"
    val simpleMethodSignRexStr = methodNameRegexStr + "\\(" + singleParamRegexStr + "\\)\\s*;\\s*"
    val methodSign = "  int insert(@Param(\"kdtId\") BuyerAddressDO buyerAddressDO); "
    val simpleMethodSignRex = simpleMethodSignRexStr.r
    val simpleMethodSignRex(methodName, arg) = methodSign
    println(methodName + " " + arg)
    val twoParamMethodSignRegStr = methodNameRegexStr + "\\(" + singleParamRegexStr + "," + singleParamRegexStr + "\\);\\s*"
    val twoParamMethodSign = "OrderExpressDO getById(@Param(\"id\") int id, @Param(\"kdtId\") int kdtId); "
    val twoParamMethodSignRex = twoParamMethodSignRegStr.r
    val twoParamMethodSignRex(methodName2, arg1, arg2) = twoParamMethodSign
    println(List(methodName2, arg1 + ", " + arg2))







  }

}

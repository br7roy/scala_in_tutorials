 /*
  * Package com.rust.scala
  * FileName: TestLongAcc
  * Author:   Rust
  * Date:     2019/2/27 10:06
  * Description:
  * History:
  *===============================================================================================
  *   author：          time：                             version：           desc：
  *   Rust           2019/2/27  10:06                      1.0
  *===============================================================================================
  */
 package com.rust.scala.single;

 import java.util.concurrent.atomic.LongAccumulator;

 /**
  * @author Rust
  */
 public class TestLongAcc {
	 public static void main(String[] args) {
		 LongAccumulator lac = new LongAccumulator((x, y) -> x + y, 3L);

		 System.out.println(lac.get());


		//




	 }

	 static LongAccumulator getInstance() {
		 return new LongAccumulator((x, y) -> x + y, 3L);
	 }
 }

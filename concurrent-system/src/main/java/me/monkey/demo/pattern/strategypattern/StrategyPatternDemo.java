package me.monkey.demo.pattern.strategypattern;

public class StrategyPatternDemo {
   public static void main(String[] args) {
      Context context = new Context(new OperationAdd());    
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationSubtract());      
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationMultiply());    
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
   }


   /*
   卫语句：
   if (isSunshine()) {
       // 晴天时处理逻辑
   　　return xx;
   }
   if (isRain()) {
       // 下雨时处理逻辑
   }
   if (isOvercast()) {
       // 阴天时处理逻辑
   }
    */
}
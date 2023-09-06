package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws MyException {
//        [1]
//        double [] arr =  new double[]{2,1,4,2,3};
//        System.out.println(Arrays.toString(task(arr)));
//        [2]
          List<OrderService> ordersServices = new ArrayList<>();
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "EUR", 1901L));
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "EUR", 2000L));
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "USD", 15L));
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "RUB", 200L));
          ordersServices.add(new OrderService(OrderService.Type.PICKUP, "RUB", 1250L));
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "USD", 35L));
          ordersServices.add(new OrderService(OrderService.Type.PICKUP, "USD", 55L));
          ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "RUB", 100L));

          System.out.println(getMaxMinusMinDeliveryMapByCurrency(ordersServices));
    }

//    public static double[] task(double[] a) throws Exception {
//        List<Double> list = new LinkedList<>();
//
//        for (double item : a) {
//            list.add(item);
//            if (item < 0) {
//                throw new MyException("There is an element less than zero in the array");
//            }
//        }
//
//        Collections.reverse(list);
//        list = list.stream().distinct().collect(Collectors.toList());
//        Collections.reverse(list);
//
//        return list.stream().mapToDouble(item -> item).toArray();
//    }

    public static Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<OrderService> orderDataList) {
        Map<String, TreeSet<Long>> ordersGroupByCurrency = orderDataList
                .stream()
                .filter(item -> OrderService.Type.DELIVERY == item.getType())
                .collect(Collectors.groupingBy(OrderService::getCurrency, Collectors.mapping(OrderService::getAmount, Collectors.toCollection(TreeSet::new))));

        Map<String, Double> finallyOrderMap = new HashMap<>();
        ordersGroupByCurrency.forEach((currency, amount) -> finallyOrderMap.put(currency, (double) (amount.last() - amount.first())));
        return finallyOrderMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    static class MyException extends Exception{
        public MyException(String message){
            super(message);
        }
    }
}
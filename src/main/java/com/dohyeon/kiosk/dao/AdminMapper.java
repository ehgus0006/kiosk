package com.dohyeon.kiosk.dao;

import com.dohyeon.kiosk.dto.ChartDTO;
import com.dohyeon.kiosk.dto.QsaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select MONTH(`regdate`) AS `date`, sum(`total_price`) AS `totalPrice` from orders WHERE orders.status='ORDER_COMPLETE' group by `date` ORDER BY `date` ASC")
    List<ChartDTO> MonthPrice();

    @Select("SELECT DATE(`regdate`) AS `date`," +
            "       sum(`total_price`) AS totalPrice" +
            "  FROM orders" +
            "  WHERE orders.`status`='ORDER_COMPLETE'" +
            " GROUP BY `date`" +
            " ORDER BY `date` ASC")
    List<ChartDTO> DayPrice();

    @Select("SELECT DATE_FORMAT(DATE_SUB(`regdate`, INTERVAL (DAYOFWEEK(`regdate`)-1) DAY), '%Y/%m/%d') as start," +
            "       DATE_FORMAT(DATE_SUB(`regdate`, INTERVAL (DAYOFWEEK(`regdate`)-7) DAY), '%Y/%m/%d') as end," +
            "       DATE_FORMAT(`regdate`, '%Y%U') AS `date`," +
            "       sum(`total_price`) AS `totalPrice`" +
            "  FROM orders" +
            "  WHERE orders.status='ORDER_COMPLETE'" +
            " GROUP BY `date`"+
            " ORDER BY `date` ASC")
    List<ChartDTO> weeklyPrice();

    @Select("SELECT DATE_FORMAT(order_menu.regdate, '%Y-%m-%d') AS `date`," +
            "       sum(order_menu.order_price) AS menu_price," +
            "       order_menu.menu_code," +
            "       sum(order_menu.count) AS menu_quantity," +
            "       menu.menu_name" +
            "  FROM orders JOIN order_menu" +
            "  ON orders.order_id=order_menu.order_id" +
            "  JOIN menu ON order_menu.menu_code=menu.menu_code" +
            "  WHERE orders.status='ORDER_COMPLETE'" +
            " GROUP BY `date`,order_menu.menu_code")
    List<QsaDTO>GlanceSalesAnalysis();


    @Select("SELECT DATE_FORMAT(DATE_SUB(order_menu.regdate, INTERVAL (DAYOFWEEK(order_menu.regdate)-1) DAY), '%Y/%m/%d') as start," +
            "       DATE_FORMAT(DATE_SUB(order_menu.regdate, INTERVAL (DAYOFWEEK(order_menu.regdate)-7) DAY), '%Y/%m/%d') as end," +
            "       DATE_FORMAT(order_menu.regdate, '%Y%U') AS `date`," +
            "       sum(order_menu.order_price) AS menu_price," +
            "       order_menu.menu_code," +
            "       sum(order_menu.count) AS menu_quantity," +
            "       menu.menu_name" +
            "  FROM orders JOIN order_menu" +
            "  ON orders.order_id=order_menu.order_id" +
            "  JOIN menu ON order_menu.menu_code=menu.menu_code" +
            " GROUP BY `date`, order_menu.menu_code")
    List<QsaDTO>WeeklySalesAnalysis();
}

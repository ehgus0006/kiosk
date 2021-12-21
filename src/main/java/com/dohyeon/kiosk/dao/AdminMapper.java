package com.dohyeon.kiosk.dao;

import com.dohyeon.kiosk.dto.ChartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select MONTH(`regdate`) AS `date`, sum(`total_price`) AS `totalPrice` from orders group by `date`")
    List<ChartDTO> MonthPrice();

    @Select("SELECT DATE(`regdate`) AS `date`, sum(`total_price`) AS `totalPrice`" +
            "  FROM orders" +
            " GROUP BY `date`")
    List<ChartDTO> DayPrice();

    @Select("SELECT DATE_FORMAT(DATE_SUB(`regdate`, INTERVAL (DAYOFWEEK(`regdate`)-1) DAY), '%Y/%m/%d') as start," +
            "       DATE_FORMAT(DATE_SUB(`regdate`, INTERVAL (DAYOFWEEK(`regdate`)-7) DAY), '%Y/%m/%d') as end," +
            "       DATE_FORMAT(`regdate`, '%Y%U') AS `date`," +
            "       sum(`total_price`) AS `totalPrice`" +
            "  FROM orders" +
            " GROUP BY `date`")
    List<ChartDTO> weeklyPrice();
}

package com.dohyeon.kiosk.test;

import com.dohyeon.kiosk.entity.*;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.repository.OrderRepository;
import com.dohyeon.kiosk.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class OrderTest {


    @Autowired
    MenuRepository menuRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    OrderRepo

    @Test
    public void 주문_테스트() {

        // given
        Admin admin = Admin.builder().admin_code(1L).build(); //get
        Menu menu = createMenu("봉골레스파게티", 8900, "0", 1, Category.valueOf("SPAGHETTI"), admin);
        int orderCount = 2;

        // when
        Long orderId = orderService.order(menu.getMenu_code(), orderCount);

        System.out.println("orderId 테스트" + orderId);
        // then
        Order getOrder = orderRepository.findById(orderId).get();

        List<OrderMenu> orderMenus = getOrder.getOrderMenus();
        for(OrderMenu orderMenu : orderMenus){
            System.out.println("getOrderMenu" + orderMenus);
        }

    }

    private Menu createMenu(String menu_name, int menu_price, String menu_stat, int menu_priority, Category category, Admin admin) {
        Menu menu = Menu.builder()
                .menu_name(menu_name)
                .menu_price(menu_price)
                .menu_stat("0")
                .menu_priority(1)
                .category(Category.SPAGHETTI)
                .admin(Admin.builder().admin_code(1L).build())
                .build();
        menuRepository.save(menu);

        return menu;
    }
}

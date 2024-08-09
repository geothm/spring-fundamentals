package ro.wantsome.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.wantsome.eshop.domain.Order;
import ro.wantsome.eshop.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orders(Model model) {

        model.addAttribute("orders", orderService.findAll());

        return "orders";
    }

    @GetMapping("/orderForm")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());

        return "orderForm";
    }

    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute Order order) {
        orderService.save(order);

        return "redirect:/orders";
    }

    @GetMapping("/updateOrder/{id}")
    public String updateOrderForm(@PathVariable long id, Model model) {
        Order order = orderService.findById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "orderForm";
        } else {
            return "redirect:/orders";
        }
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }
}

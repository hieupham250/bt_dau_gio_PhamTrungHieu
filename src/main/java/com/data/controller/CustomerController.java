package com.data.controller;

import com.data.dto.CustomerDTO;
import com.data.entity.Customer;
import com.data.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String customer(@RequestParam(defaultValue = "") String keyword, Model model) {
        List<Customer> customers = customerService.findAll(keyword);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setImage(customerDTO.getImage());

        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) return "redirect:/customers";

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setImage(customer.getImage());

        model.addAttribute("customerDTO", customerDTO);
        return "editCustomer";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable int id, @ModelAttribute("customerDTO") @Valid CustomerDTO customerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editCustomer";
        }

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setImage(customerDTO.getImage());

        customerService.update(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}

package com.example.itemservice.web.basic;

import com.example.itemservice.domain.item.Item;
import com.example.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {
    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping
    public String items(Model model) {
        log.info("[GET] /basic/items");

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        log.info("[GET] /basic/items/{}", itemId);

        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/save")
    public String saveForm() {
        log.info("[GET] /basic/items/save");
        return "basic/saveForm";
    }

    /**
     * @RequestParam
     */
//    @PostMapping("/save")
    public String saveV1(@RequestParam String itemName,
                         @RequestParam int itemPrice,
                         @RequestParam Integer itemQuantity,
                         Model model) {
        log.info("[POST] /basic/items/save  V1");

        Item item = new Item();
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        item.setItemQuantity(itemQuantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }


    /**
     * @ModelAttribute("item") Item item
     * model.addAttribute("item", item); 자동 추가
     */
//    @PostMapping("/save")
    public String saveV2(@ModelAttribute("item") Item item, Model model) {
        log.info("[POST] /basic/items/save  V2");
        log.info("Item = {}", item);

        itemRepository.save(item);
        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

    /**
     * @ModelAttribute name 생략 가능
     * model.addAttribute(item); 자동 추가, 생략 가능
     * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
     */
//    @PostMapping("/save")
    public String saveV3(@ModelAttribute Item item) {
        log.info("[POST] /basic/items/save  V3");
        log.info("Item = {}", item);

        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * @ModelAttribute 자체 생략 가능
     * model.addAttribute(item) 자동 추가
     */
//    @PostMapping("/save")
    public String saveV4(Item item) {
        log.info("[POST] /basic/items/save  V4");
        log.info("Item = {}", item);
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * PRG - Post/Redirect/Get
     */
//    @PostMapping("/save")
    public String saveV5(Item item) {
        log.info("[POST] /basic/items/save  V5");
        log.info("Item = {}", item);

        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getItemId();
    }

    /**
     * RedirectAttributes
     */
    @PostMapping("/save")
    public String saveV6(Item item, RedirectAttributes redirectAttributes) {
        log.info("[POST] /basic/items/save  V6");
        log.info("Item = {}", item);

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getItemId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/update")
    public String updateForm(@PathVariable Long itemId, Model model) {
        log.info("[GET] /basic/items/{}/update", itemId);

        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/updateForm";
    }

    @PostMapping("/{itemId}/update")
    public String update(@PathVariable Long itemId, @ModelAttribute Item item) {
        log.info("[POST] /basic/items/{}/update", itemId);
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long itemId) {
        log.info("[POST] /basic/items/delete  itemId={}", itemId);
        itemRepository.delete(itemId);
        return "redirect:/basic/items";
    }
}

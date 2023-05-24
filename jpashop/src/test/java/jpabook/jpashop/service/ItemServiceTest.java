package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_저장() throws Exception{
        //given
        Item book = new Book();
        book.setName("kim");
        book.setStockQuantity(10);

        //when
        itemService.saveItem(book);

        //then
        assertEquals(book, itemRepository.findOne(book.getId()));
        System.out.println("item1 = " + book);
        System.out.println("itemRepository = " + itemRepository.findOne(book.getId()));

    }

    @Test(expected = NotEnoughStockException.class)
    public void 주문_취소()throws Exception{
        //given
        Item book = new Book();
        book.setPrice(1000);
        book.setName("ㅁㅁㅁ");
        book.setStockQuantity(10);
        //when
        book.removeStock(11);

        //then
        fail("재고가 부족해야 합니다.");

    }

}
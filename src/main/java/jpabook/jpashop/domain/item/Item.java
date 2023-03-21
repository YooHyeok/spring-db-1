package jpabook.jpashop.domain.item;

import jpabook.jpashop.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계 매핑 전략 지정(싱글테이블 전략 - Entity가 3개로 나뉘어 따로 관리되지만 부모테이블인 Item테이블 하나로 정의된다.)
@DiscriminatorColumn(name = "dtype") //Book, Album, Movie 엔티티의 @DiscriminatorValue의 구분값을 통해 구분
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity; //재고 수량
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==//
    // 도메인 주도 설계에서는 엔티티 안에 비즈니스 로직을 넣어주는게 좋다. 높은 응집도

    /**
     * stock - 재고 수량 증가 로직
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

}

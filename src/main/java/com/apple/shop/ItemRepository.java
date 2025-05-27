package com.apple.shop;

import org.springframework.data.jpa.repository.JpaRepository;
// itemRepository 인터페이스는 JpaRepository를 상속받아 CRUD 기능을 제공합니다.
// // JpaRepository<Item, Long>에서 Item은 엔티티 클래스이고, Long은 엔티티의 ID 타입입니다.
public interface ItemRepository extends JpaRepository<Item, Long> {

}

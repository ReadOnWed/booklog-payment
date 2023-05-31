package com.booklog.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface WishlistRepository extends CrudRepository<HashSet<Integer>, String> {
}

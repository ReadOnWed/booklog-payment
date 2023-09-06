package com.booklog.payment.domain.wishlist.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booklog.payment.domain.wishlist.dto.WishlistDto;
import com.booklog.payment.domain.wishlist.entity.ProductWishlist;
import com.booklog.payment.domain.wishlist.repository.ProductWishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final ProductWishlistRepository productWishlistRepository;

	@Override
	@Transactional
	public void addWishList(WishlistDto wishlistDto) {
		Optional<ProductWishlist> productWishlist = productWishlistRepository.findProductWishlistByProductId(
			wishlistDto.getProductId());

		if (productWishlist.isEmpty()) {
			productWishlistRepository.save(createProductWishlist(wishlistDto));
			return;
		}

		Set<Long> memberIds = productWishlist.get().getMemberIds();
		memberIds.add(wishlistDto.getMemberId());
		productWishlist.get().updateMemberIds(memberIds);

		productWishlistRepository.save(productWishlist.get());
	}

	ProductWishlist createProductWishlist(WishlistDto wishlistDto) {
		Set<Long> members = new HashSet<>();
		members.add(wishlistDto.getMemberId());
		return new ProductWishlist(wishlistDto.getProductId(), members);
	}
}

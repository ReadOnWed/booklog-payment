package com.booklog.payment.domain.wishlist.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booklog.payment.domain.wishlist.dto.WishlistDto;
import com.booklog.payment.domain.wishlist.entity.MemberWishlist;
import com.booklog.payment.domain.wishlist.entity.ProductWishlist;
import com.booklog.payment.domain.wishlist.repository.MemberWishlistRepository;
import com.booklog.payment.domain.wishlist.repository.ProductWishlistRepository;
import com.booklog.payment.global.error.ErrorCode;
import com.booklog.payment.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final ProductWishlistRepository productWishlistRepository;
	private final MemberWishlistRepository memberWishlistRepository;

	@Override
	@Transactional
	public WishlistDto.MemberWishlistDto addWishList(WishlistDto wishlistDto) {
		addInProductWishList(wishlistDto);
		return WishlistDto.MemberWishlistDto.from(addInMemberWishList(wishlistDto));
	}

	@Override
	@Transactional
	public WishlistDto.MemberWishlistDto deleteWishList(WishlistDto wishlistDto) {
		removeInProductWishList(wishlistDto);
		return WishlistDto.MemberWishlistDto.from(removeInMemberWishList(wishlistDto));
	}

	@Override
	public WishlistDto.MemberCountDto getWishMemberCount(Long productId) {
		ProductWishlist productWishlist = productWishlistRepository.findProductWishlistByProductId(
			productId).orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_WISHLIST));

		return WishlistDto.MemberCountDto.from(productWishlist);
	}

	@Override
	public WishlistDto.MemberWishlistDto getMyWishlist(Long memberId) {
		MemberWishlist memberWishlist = memberWishlistRepository.findProductWishlistByMemberId(
			memberId).orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_WISHLIST));

		return WishlistDto.MemberWishlistDto.from(memberWishlist);
	}

	private MemberWishlist removeInMemberWishList(WishlistDto wishlistDto) {
		MemberWishlist memberWishlist = memberWishlistRepository.findProductWishlistByMemberId(
			wishlistDto.getMemberId()).orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_WISHLIST));

		Set<Long> productIds = memberWishlist.getProductIds();
		productIds.remove(wishlistDto.getProductId());
		memberWishlist.updateProductIds(productIds);

		return memberWishlistRepository.save(memberWishlist);
	}

	private void removeInProductWishList(WishlistDto wishlistDto) {
		ProductWishlist productWishlist = productWishlistRepository.findProductWishlistByProductId(
			wishlistDto.getProductId()).orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_WISHLIST));

		Set<Long> memberIds = productWishlist.getMemberIds();
		memberIds.remove(wishlistDto.getMemberId());
		productWishlist.updateMemberIds(memberIds);

		productWishlistRepository.save(productWishlist);
	}

	private MemberWishlist addInMemberWishList(WishlistDto wishlistDto) {
		Optional<MemberWishlist> memberWishlist = memberWishlistRepository.findProductWishlistByMemberId(
			wishlistDto.getMemberId());

		if (memberWishlist.isEmpty()) {
			return memberWishlistRepository.save(createMemberWishlist(wishlistDto));
		}

		Set<Long> productIds = memberWishlist.get().getProductIds();
		productIds.add(wishlistDto.getProductId());
		memberWishlist.get().updateProductIds(productIds);

		return memberWishlistRepository.save(memberWishlist.get());
	}

	private void addInProductWishList(WishlistDto wishlistDto) {
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

	private ProductWishlist createProductWishlist(WishlistDto wishlistDto) {
		Set<Long> members = new HashSet<>();
		members.add(wishlistDto.getMemberId());
		return new ProductWishlist(wishlistDto.getProductId(), members);
	}

	private MemberWishlist createMemberWishlist(WishlistDto wishlistDto) {
		Set<Long> products = new HashSet<>();
		products.add(wishlistDto.getProductId());
		return new MemberWishlist(wishlistDto.getMemberId(), products);
	}
}

package es.urjc.code.daw.compartiendoPiso.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Page<Review> findByOfferReview(Offer offer,Pageable page);
}

package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.daw.compartiendoPiso.review.Review;



public interface OfferRepository extends JpaRepository<Offer,Long>{
	
	@Query(value="SELECT * FROM offer where (title LIKE %?1% OR location LIKE %?1% OR province LIKE %?1% OR neighborhood=?1 OR ?1='') "
			+ "AND ((price<=?4 AND price>=?3) OR (price>=?3 AND ?4=0) OR (price<=?4 AND ?3=0) OR (?3=0 AND ?4=0))"
			+ "AND (type=?2 OR ?2='empty') AND (area>=?5 OR ?5=0) AND (rooms>=?6 OR ?6=0) AND (bathroom>=?7 OR ?7=0)", nativeQuery=true)
	List<Offer> masterQuery(String queryBox, String type, float priceFrom, float priceTo, int area, int rooms, int bathroom);
}

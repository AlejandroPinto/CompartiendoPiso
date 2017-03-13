package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.daw.compartiendoPiso.review.Review;



public interface OfferRepository extends JpaRepository<Offer,Long>{
	
//	@Query(value="SELECT * FROM offer where (title LIKE %?1% OR location LIKE %?1% OR province LIKE %?1% OR neighborhood=?1 OR ?1='') "
//			+ "AND ((price<=?4 AND price>=?3) OR (price>=?3 AND ?4=0) OR (price<=?4 AND ?3=0) OR (?3=0 AND ?4=0))"
//			+ "AND (type=?2 OR ?2='empty') AND (area>=?5 OR ?5=0) AND (rooms>=?6 OR ?6=0) AND (bathroom>=?7 OR ?7=0)", nativeQuery=true)
	@Query(value="SELECT o.* FROM offer o, characteristics c where (o.title LIKE %?1% OR o.location LIKE %?1% OR o.province LIKE %?1% OR o.neighborhood=?1 OR ?1='') "
			+ "AND ((o.price<=?4 AND o.price>=?3) OR (o.price>=?3 AND ?4=0) OR (o.price<=?4 AND ?3=0) OR (?3=0 AND ?4=0))"
			+ "AND (o.type=?2 OR ?2='empty') AND (o.area>=?5 OR ?5=0) AND (o.rooms>=?6 OR ?6=0) AND (o.bathroom>=?7 OR ?7=0)"
			+ "AND ((c.name=?8 AND o.id=c.offer_id) OR (?8='')) AND ((c.name=?9 AND o.id=c.offer_id) OR (?9='')) AND ((c.name=?10 AND o.id=c.offer_id) OR (?10=''))", nativeQuery=true)
	List<Offer> masterQuery(String queryBox, String type, float priceFrom, float priceTo, int area, int rooms, int bathroom,String a1,String a2, String a3);
}

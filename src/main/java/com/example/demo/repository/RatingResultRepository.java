public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
RatingResult findByPropertyId(Long propertyId);
}
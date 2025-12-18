public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
List<RatingLog> findByPropertyId(Long propertyId);
}
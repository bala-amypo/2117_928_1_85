public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
FacilityScore findByPropertyId(Long propertyId);
}
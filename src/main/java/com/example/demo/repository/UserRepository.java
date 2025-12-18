public interface UserRepository extends JpaRepository<User, Long> {
boolean existsByEmail(String email);
User findByEmail(String email);
}
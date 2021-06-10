package platform;

import org.springframework.data.repository.CrudRepository;

public interface CodeRepository extends CrudRepository<Code, String> {

    Iterable<Code> findFirst10BySecretOrderByUpdateDateDesc(boolean secret);

}

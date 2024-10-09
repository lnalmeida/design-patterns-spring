package dio.edu.design_patterns_spring.service.interfaces;

import  dio.edu.design_patterns_spring.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API de CEPs do
 * <b>Brasil API</b>.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://brasilapi.com.br/docs">Brasil API</a>
 *
 * @author lnalmeida
 */

@FeignClient(name = "brasilapi", url = "https://brasilapi.com.br/api/cep/v1")
public interface IBuscaEnderecoService {
    @GetMapping("/{cep}")
    Endereco consultarEndereçoPorCep(@PathVariable("cep") String cep);
}

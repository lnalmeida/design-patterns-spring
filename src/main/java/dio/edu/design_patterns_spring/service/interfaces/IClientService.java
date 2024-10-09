package dio.edu.design_patterns_spring.service.interfaces;

import dio.edu.design_patterns_spring.model.Cliente;

public interface IClientService {

    Iterable<Cliente> buscarClientes();
    Cliente buscarClientePorId(Long id);
    Cliente adicionarNovoCliente(Cliente cliente);
    void atualizarCliente(Long id, Cliente cliente);
    void excluirCliente(Long id);
}

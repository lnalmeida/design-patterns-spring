package dio.edu.design_patterns_spring.service;

import dio.edu.design_patterns_spring.model.Cliente;
import dio.edu.design_patterns_spring.model.Endereco;
import dio.edu.design_patterns_spring.repository.ClienteRepository;
import dio.edu.design_patterns_spring.repository.EnderecoRepository;
import dio.edu.design_patterns_spring.service.interfaces.IBuscaEnderecoService;
import dio.edu.design_patterns_spring.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClientService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private IBuscaEnderecoService buscaEnderecoService;

    @Override
    public Iterable<Cliente> buscarClientes() {
       return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente adicionarNovoCliente(Cliente cliente) {
        salvarClienteCompleto(cliente);
        return cliente;
    }

    @Override
    public void atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteToUpdate = clienteRepository.findById(id);
        if (clienteToUpdate.isPresent()) {
            salvarClienteCompleto(cliente);
        }
    }

    @Override
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteCompleto(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Optional<Endereco> endereco = enderecoRepository.findById(cep);
       if(endereco.isEmpty()) {
            Endereco novoEndereco = buscaEnderecoService.consultarEndereçoPorCep(cep);
            enderecoRepository.save(novoEndereco);
            cliente.setEndereco(novoEndereco);
        };
        clienteRepository.save(cliente);
    }

}

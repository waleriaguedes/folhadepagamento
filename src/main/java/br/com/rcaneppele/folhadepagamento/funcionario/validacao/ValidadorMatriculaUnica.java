package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorMatriculaUnica implements ValidadorCadastroFuncionario {
	
	private final FuncionarioRepository repository;
	
	@Inject
	public ValidadorMatriculaUnica(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void valida(Funcionario funcionario) throws ValidacaoException {
		Funcionario encontrado = repository.buscaPorMatricula(funcionario.getDadosProfissionais().getMatricula());

		if (encontrado != null && !encontrado.equals(funcionario)) {
			throw new ValidacaoException("Já existe outro Funcionário cadastrado com a Matrícula informada!");
		}
	}

}

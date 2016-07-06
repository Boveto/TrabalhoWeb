package br.com.localweb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

//import javax.websocket.Session;

import org.hibernate.Transaction;
import br.com.localweb.domain.Funcionario;
import br.com.localweb.util.HibernateUtil;

public class FuncionarioDAO {

	public void salvar(Funcionario Funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.save(Funcionario);

			transacao.commit();

		} catch (RuntimeException ex) {

			if (transacao != null) {

				transacao.rollback();

			}

			throw ex;

		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")

	public List<Funcionario> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Funcionario> funcionarios = null;
		try {

			Query consulta = sessao.getNamedQuery("funcionario.listar");

			funcionarios = consulta.list();

		} catch (RuntimeException ex) {

			throw ex;

		} finally {

			sessao.close();

		}

		return funcionarios;

	}

	public Funcionario buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Funcionario funcionario = null;

		try {

			Query consulta = sessao.getNamedQuery("funcionario.buscarPorCodigo");

			consulta.setLong("codigo", codigo);

			funcionario = (Funcionario) consulta.uniqueResult();

		} catch (RuntimeException ex) {

			throw ex;

		} finally {

			sessao.close();

		}

		return funcionario;

	}

	public void excluir(Funcionario funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.delete(funcionario);

			transacao.commit();

		} catch (RuntimeException ex) {

			if (transacao != null) {

				transacao.rollback();

			}

			throw ex;

		} finally {

			sessao.close();

		}

	}

	public void editar(Funcionario funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.update(funcionario);

			transacao.commit();

		} catch (RuntimeException ex) {

			if (transacao != null) {

				transacao.rollback();

			}

			throw ex;

		} finally {

			sessao.close();

		}

	}

}

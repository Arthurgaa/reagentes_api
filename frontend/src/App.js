import React, { useState, useEffect } from "react";
import "./App.css";

function App() {
  const [reagentes, setReagentes] = useState([]);
  const [form, setForm] = useState({
    nome: "",
    codigoSku: "",
    lote: "",
    dataValidade: "",
    quantidadeEmEstoque: "",
  });

  const API_URL = "http://localhost:8080/api/reagentes"; // usa o backend Spring Boot

  // Carregar reagentes
  const carregarReagentes = async () => {
    try {
      const resp = await fetch(API_URL);
      const data = await resp.json();
      setReagentes(data);
    } catch (error) {
      console.error("Erro ao carregar reagentes:", error);
    }
  };

  useEffect(() => {
    carregarReagentes();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  // 🚨 Validação simples antes de enviar
  if (!form.nome || !form.codigoSku || !form.lote || !form.dataValidade) {
    alert("Preencha todos os campos obrigatórios!");
    return;
  }

  const payload = {
    nome: form.nome.trim(),
    codigoSku: form.codigoSku.trim(),
    lote: form.lote.trim(),
    dataValidade: form.dataValidade || null,
    dataRecebimento: new Date().toISOString().split("T")[0], // data atual no formato yyyy-MM-dd
    quantidadeEmEstoque: form.quantidadeEmEstoque
      ? parseInt(form.quantidadeEmEstoque)
      : 0, // garante número válido
    status: "DISPONIVEL", // valor válido no enum
    fabricanteId: null,
    localizacaoId: null,
  };

  console.log("📦 Enviando payload:", payload); // debug no console do navegador

  try {
    const resp = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!resp.ok) {
      const msg = await resp.text();
      console.error("❌ Erro do servidor:", msg);
      alert("Erro ao cadastrar reagente. Veja o console para mais detalhes.");
      return;
    }

    const novoReagente = await resp.json();
    console.log("✅ Reagente cadastrado:", novoReagente);

    // Limpa o formulário e recarrega a lista
    setForm({
      nome: "",
      codigoSku: "",
      lote: "",
      dataValidade: "",
      quantidadeEmEstoque: "",
    });

    await carregarReagentes();
  } catch (error) {
    console.error("🚫 Erro na requisição:", error);
    alert("Erro de conexão com o servidor.");
  }
};



  const deletar = async (id) => {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarReagentes();
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Gerenciador de Reagentes</h1>

      <form
        onSubmit={handleSubmit}
        className="card p-4 shadow-sm mb-5"
        style={{ maxWidth: "600px", margin: "auto" }}
      >
        <div className="mb-3">
          <label className="form-label">Nome</label>
          <input
            name="nome"
            value={form.nome}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Código SKU</label>
          <input
            name="codigoSku"
            value={form.codigoSku}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Lote</label>
          <input
            name="lote"
            value={form.lote}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Data de Validade</label>
          <input
            type="date"
            name="dataValidade"
            value={form.dataValidade}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Quantidade em Estoque</label>
          <input
            type="number"
            name="quantidadeEmEstoque"
            value={form.quantidadeEmEstoque}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Adicionar Reagente
        </button>
      </form>

      <div className="card shadow-sm p-4">
        <h2 className="h5 mb-3">Lista de Reagentes</h2>
        {reagentes.length === 0 ? (
          <p className="text-muted text-center">Nenhum reagente cadastrado.</p>
        ) : (
          <div className="table-responsive">
            <table className="table table-bordered table-hover text-center">
              <thead className="table-light">
                <tr>
                  <th>Nome</th>
                  <th>SKU</th>
                  <th>Lote</th>
                  <th>Validade</th>
                  <th>Qtd</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                {reagentes.map((r) => (
                  <tr key={r.id}>
                    <td>{r.nome}</td>
                    <td>{r.codigoSku}</td>
                    <td>{r.lote}</td>
                    <td>{r.dataValidade}</td>
                    <td>{r.quantidadeEmEstoque}</td>
                    <td>
                      <button
                        onClick={() => deletar(r.id)}
                        className="btn btn-danger btn-sm"
                      >
                        Excluir
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;

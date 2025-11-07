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

  const API_URL = "http://localhost:8080/api/reagentes";

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

    if (!form.nome || !form.codigoSku || !form.lote || !form.dataValidade) {
      alert("Preencha todos os campos obrigatórios!");
      return;
    }

// Monta o mínimo necessário
    const base = {
      nome: (form.nome || "").trim(),
      codigoSku: (form.codigoSku || "").trim(),
      lote: (form.lote || "").trim(),
      dataValidade: form.dataValidade || null, // yyyy-MM-dd
      dataRecebimento: new Date().toISOString().split("T")[0],
      quantidadeEmEstoque: form.quantidadeEmEstoque
        ? parseInt(form.quantidadeEmEstoque, 10)
        : 0,
      status: "LIBERADO",
      // OBS: NÃO vamos enviar fabricanteId/localizacaoId quando estiverem vazios
      // fabricanteId: form.fabricanteId || null,
      // localizacaoId: form.localizacaoId || null,
    };

    // Remove chaves nulas/vazias (evita 400 por binding/validação)
    const payload = Object.fromEntries(
      Object.entries(base).filter(
        ([, v]) =>
          v !== null &&
          v !== "" &&
          !(typeof v === "number" && Number.isNaN(v))
      )
    );

    console.log("📦 Enviando payload:", payload);

    try {
      const resp = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });


      if (!resp.ok) {
        const msg = await resp.text(); // pega texto retornado pelo backend
        console.error("❌ Resposta do backend:", msg);
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
    <div className="container py-4">
      <h1 className="text-center mb-3">Gerenciador de Reagentes</h1>

      {/* === BLOCO PRINCIPAL === */}
      <div className="main-layout">
        {/* Coluna Esquerda */}
        <div className="side-column">
          <div className="top-row">
            <img src="/img/reagente1.jpg" alt="Reagente 1" className="reagente-img" />
            <img src="/img/reagente2.jpg" alt="Reagente 2" className="reagente-img" />
          </div>
          <img src="/img/reagente3.png" alt="Reagente 3" className="reagente-img" />
        </div>

        {/* Formulário Central */}
        <form onSubmit={handleSubmit} className="card p-4 shadow-sm form-card">
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

        {/* Coluna Direita */}
        <div className="side-column">
          <div className="top-row">
            <img src="/img/reagente4.png" alt="Reagente 4" className="reagente-img" />
            <img src="/img/reagente5.png" alt="Reagente 5" className="reagente-img" />
          </div>
          <img src="/img/reagente6.png" alt="Reagente 6" className="reagente-img" />
        </div>
      </div>

      {/* === TABELA === */}
      <div className="card shadow-sm p-4 mt-4">
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

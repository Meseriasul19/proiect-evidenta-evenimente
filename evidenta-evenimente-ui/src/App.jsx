import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [clienti, setClienti] = useState([]);
  
  // Stare pentru formularul de adaugare
  const [formData, setFormData] = useState({
    nume: '',
    email: '',
    telefon: ''
  });

  // Funcție pentru a încărca lista (o refolosim dupa adaugare/stergere)
  const fetchClienti = () => {
    fetch('http://localhost:8080/api/clienti')
      .then(res => res.json())
      .then(data => setClienti(data))
      .catch(err => console.error(err));
  };

  // Rulam la inceput
  useEffect(() => {
    fetchClienti();
  }, []);

  // 1. Funcția de ȘTERGERE
  const handleDelete = async (id) => {
    if(!window.confirm("Sigur vrei să ștergi acest client?")) return;

    await fetch(`http://localhost:8080/api/clienti/${id}`, {
      method: 'DELETE'
    });
    // Reîncărcăm lista după ștergere
    fetchClienti();
  };

  // 2. Funcția de ADĂUGARE
  const handleAdd = async (e) => {
    e.preventDefault(); // Oprește reîncărcarea paginii

    await fetch('http://localhost:8080/api/clienti', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    });

    // Resetăm formularul și reîncărcăm lista
    setFormData({ nume: '', email: '', telefon: '' });
    fetchClienti();
  };

  // Functie helper pentru input-uri
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      <h1>Manager Clienți</h1>

      {/* --- FORMULAR ADĂUGARE --- */}
      <div style={{ background: '#f9f9f9', padding: '15px', borderRadius: '8px', marginBottom: '20px' }}>
        <h3 style={{ color: 'black' }}>Adaugă Client Nou</h3>
        <form onSubmit={handleAdd} style={{ display: 'flex', gap: '10px' }}>
          <input 
            name="nume" 
            placeholder="Nume complet" 
            value={formData.nume} 
            onChange={handleChange} 
            required 
          />
          <input 
            name="email" 
            placeholder="Email" 
            value={formData.email} 
            onChange={handleChange} 
            required 
          />
          <input 
            name="telefon" 
            placeholder="Telefon" 
            value={formData.telefon} 
            onChange={handleChange} 
          />
          <button type="submit" style={{ background: 'green', color: 'white' }}>Salvează</button>
        </form>
      </div>

      {/* --- TABEL DATE --- */}
      <table border="1" style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ background: '#333', color: 'white' }}>
            <th>ID</th>
            <th>Nume</th>
            <th>Email</th>
            <th>Telefon</th>
            <th>Acțiuni</th>
          </tr>
        </thead>
        <tbody>
          {clienti.map(client => (
            <tr key={client.id}>
              <td style={{ textAlign: 'center' }}>{client.id}</td>
              <td>{client.nume}</td>
              <td>{client.email}</td>
              <td>{client.telefon}</td>
              <td style={{ textAlign: 'center' }}>
                <button 
                  onClick={() => handleDelete(client.id)}
                  style={{ background: 'red', color: 'white', border: 'none', padding: '5px 10px', cursor: 'pointer' }}
                >
                  Șterge
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
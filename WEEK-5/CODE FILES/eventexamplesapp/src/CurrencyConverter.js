
import React, { useState } from "react";

function CurrencyConverter() {
  const [amount, setAmount] = useState("");
  const [currency, setCurrency] = useState("Euro");
  const [result, setResult] = useState("");

  const convertCurrency = () => {
    let convertedAmount = 0;

    if (currency === "Euro") {
      convertedAmount = amount * 90;
    } else if (currency === "USD") {
      convertedAmount = amount * 83;
    }

    setResult(convertedAmount);
  };

  return (
    <div>
      <div className="row">
        <label>Amount:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>

      <div className="row">
        <label>Currency:</label>
        <select
          value={currency}
          onChange={(e) => setCurrency(e.target.value)}
        >
          <option>Euro</option>
          <option>USD</option>
        </select>
      </div>

      <button onClick={convertCurrency}>Submit</button>

      <h3>{result && `Converted Amount: ₹${result}`}</h3>
    </div>
  );
}

export default CurrencyConverter;
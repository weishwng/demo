const express = require('express');
const path = require('path');
const app = express();
const port = process.env.PORT || 5000;

app.use(express.static(path.join(__dirname)));

app.get('/api/ping', (req, res) => res.json({ ok: true, time: Date.now() }));

app.listen(port, () => console.log(`Server servito su http://localhost:${port}`));
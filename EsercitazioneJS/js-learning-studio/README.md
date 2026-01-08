# JS Learning Studio — MVP

Questo è il punto di partenza per un progetto didattico che aiuta a comprendere JavaScript.

## Funzionalità MVP
- Editor (textarea) con esempi
- Sandbox sicuro (iframe sandboxed) per eseguire codice e catturare console
- Parser AST (Acorn via unpkg) e visualizzazione
- Trace dei log runtime

## Avvio rapido
- Aperto semplicemente: `npx http-server . -p 5173` nella cartella `js-learning-studio` e apri http://localhost:5173/js-learning-studio
- Oppure: `npm run dev` se preferisci usare Vite (dopo `npm install` se vuoi)

## Next steps
- Sostituire l'editor con Monaco o CodeMirror
- Implementare execution tracer (step-by-step), microtasks visualizer e supporto per esecuzione Node sandbox
- Aggiungere esempio "From Java" per comparazioni concettuali

Buon divertimento!
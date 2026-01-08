import { setupEditor } from './editor.js';
import { createSandbox } from './sandbox.js';
import { parse, prettyAst } from './ast.js';

const textarea = document.getElementById('editor');
const runBtn = document.getElementById('run');
const formatBtn = document.getElementById('format');
const output = document.getElementById('output');
const astEl = document.getElementById('ast');
const trace = document.getElementById('trace');
const examples = document.getElementById('examples');
const iframe = document.getElementById('sandbox');

const exList = [
  { title: 'Closure', code: textarea.value },
  { title: 'This behavior', code: `const obj = {x: 1, f(){ console.log(this.x); }}; obj.f(); const f = obj.f; f();` },
  { title: 'Async + Promise', code: `async function test(){ console.log('start'); await Promise.resolve(); console.log('after'); } test(); console.log('sync end');` }
];

const ed = setupEditor(textarea, examples, exList);
const sandbox = createSandbox(iframe);

// messaging from iframe
window.addEventListener('message', (ev) => {
  const { type, payload } = ev.data || {};
  if (!type) return;
  if (type === 'log' || type === 'info') {
    appendOutput('log', payload);
    appendTrace('log', payload);
  }
  if (type === 'warn') appendOutput('warn', payload);
  if (type === 'error') appendOutput('error', payload);
  if (type === 'result') appendOutput('result', payload);
});

function appendOutput(kind, payload) {
  const line = document.createElement('div');
  line.textContent = `[${kind}] ${Array.isArray(payload) ? payload.join(' ') : payload}`;
  output.appendChild(line);
  output.scrollTop = output.scrollHeight;
}

function appendTrace(kind, payload) {
  const line = document.createElement('div');
  line.textContent = `${new Date().toLocaleTimeString()} [${kind}] ${payload.map(p => JSON.stringify(p)).join(' ')}`;
  trace.appendChild(line);
  trace.scrollTop = trace.scrollHeight;
}

runBtn.addEventListener('click', async () => {
  output.textContent = '';
  trace.textContent = '';
  const code = ed.getCode();
  // parse AST
  try {
    const ast = await parse(code);
    astEl.textContent = prettyAst(ast);
  } catch (err) {
    astEl.textContent = 'Parse error: ' + String(err);
  }
  // run in sandbox
  sandbox.postCode(code);
});

formatBtn.addEventListener('click', () => {
  // simple formatting: using indentation via a small trick with Function + toString for functions is not robust
  // keep simple: trim and ensure trailing newline
  const c = ed.getCode().trim() + '\n';
  ed.setCode(c);
});

// auto-parse on load
runBtn.click();

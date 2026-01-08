export function setupEditor(textarea, examplesSelect, examples) {
  // populate examples
  examples.forEach((ex, i) => {
    const opt = document.createElement('option');
    opt.value = i; opt.textContent = ex.title;
    examplesSelect.appendChild(opt);
  });

  examplesSelect.addEventListener('change', () => {
    const i = Number(examplesSelect.value);
    textarea.value = examples[i].code;
    textarea.dispatchEvent(new Event('input'));
  });

  // basic auto-resize
  const resize = () => textarea.style.height = (textarea.scrollHeight + 8) + 'px';
  textarea.addEventListener('input', resize);
  resize();

  return {
    getCode: () => textarea.value,
    setCode: (c) => { textarea.value = c; resize(); }
  };
}
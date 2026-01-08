import { App } from './app.js';
import { qs } from './utils.js';

const app = new App();

const form = qs('#task-form');
const input = qs('#task-input');
const filter = qs('#filter');
const clearBtn = qs('#clear-completed');

form.addEventListener('submit', e => {
  e.preventDefault();
  const v = input.value.trim();
  if (!v) return;
  app.create(v);
  input.value = '';
});

filter.addEventListener('change', () => {
  app.render(filter.value);
});

clearBtn.addEventListener('click', () => app.clearCompleted());
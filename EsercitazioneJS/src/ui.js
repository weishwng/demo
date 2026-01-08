import { uid, qs } from './utils.js';

const taskTemplate = (t) => `
  <li class="task ${t.completed ? 'complete' : ''}" data-id="${t.id}">
    <input type="checkbox" class="toggle" ${t.completed ? 'checked' : ''}/>
    <span class="title">${escape(t.title)}</span>
    <button class="edit small">Modifica</button>
    <button class="del small">Elimina</button>
  </li>`;

export function renderList(tasks, container) {
  container.innerHTML = tasks.map(taskTemplate).join('');
}

export function escape(str){ return String(str).replaceAll('<','&lt;').replaceAll('>','&gt;'); }

export function bindEvents(container, handlers) {
  container.addEventListener('click', (e) => {
    const li = e.target.closest('li.task');
    if (!li) return;
    const id = li.dataset.id;
    if (e.target.matches('.toggle')) handlers.toggle(id);
    if (e.target.matches('.del')) handlers.delete(id);
    if (e.target.matches('.edit')) handlers.edit(id);
  });
}
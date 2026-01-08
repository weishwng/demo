import { load, save } from './store.js';
import { renderList, bindEvents } from './ui.js';
import { uid, qs } from './utils.js';

export class App {
  constructor() {
    this.tasks = load();
    this.container = qs('#task-list');
    bindEvents(this.container, {
      toggle: (id) => this.toggle(id),
      delete: (id) => this.remove(id),
      edit: (id) => this.promptEdit(id)
    });
    this.render();
  }

  create(title) {
    const t = { id: uid(), title, completed:false, created:Date.now() };
    this.tasks.unshift(t);
    this._saveRender();
  }

  toggle(id) {
    this.tasks = this.tasks.map(t => t.id === id ? {...t, completed: !t.completed} : t);
    this._saveRender();
  }

  remove(id) {
    this.tasks = this.tasks.filter(t => t.id !== id);
    this._saveRender();
  }

  edit(id, newTitle) {
    this.tasks = this.tasks.map(t => t.id === id ? {...t, title:newTitle} : t);
    this._saveRender();
  }

  promptEdit(id) {
    const t = this.tasks.find(x => x.id === id);
    const title = prompt('Modifica attività', t.title);
    if (title !== null && title.trim()) this.edit(id, title.trim());
  }

  _saveRender() {
    save(this.tasks);
    this.render();
  }

  filterBy(mode) {
    if (mode === 'active') return this.tasks.filter(t => !t.completed);
    if (mode === 'completed') return this.tasks.filter(t => t.completed);
    return this.tasks;
  }

  render(filter='all') {
    const shown = this.filterBy(filter);
    renderList(shown, this.container);
  }

  clearCompleted() {
    this.tasks = this.tasks.filter(t => !t.completed);
    this._saveRender();
  }
}
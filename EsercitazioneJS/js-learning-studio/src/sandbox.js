// sandbox.js — creates an iframe sandbox and handles execution
export function createSandbox(iframe) {
  // prepare content for iframe: listens for code, executes and posts back console logs/errors
  const html = `<!doctype html><html><body>
<script>
  // capture console
  (function(){
    const orig = console;
    const send = (type, args) => parent.postMessage({type, payload: args}, '*');
    console = {
      log: (...a) => { send('log', a); orig.log.apply(orig, a); },
      info: (...a) => { send('info', a); orig.info.apply(orig, a); },
      warn: (...a) => { send('warn', a); orig.warn.apply(orig, a); },
      error: (...a) => { send('error', a); orig.error.apply(orig, a); }
    };

    window.addEventListener('message', async (ev) => {
      const { code, id } = ev.data || {};
      if (!code) return;
      try {
        // Use Function to limit scope and return value
        const fn = new Function(code);
        const res = fn();
        if (res instanceof Promise) {
          res.then(r => parent.postMessage({type:'result', payload: r, id}, '*'))
            .catch(err => parent.postMessage({type:'error', payload: String(err), id}, '*'));
        } else {
          parent.postMessage({type:'result', payload: res, id}, '*');
        }
      } catch (err) {
        parent.postMessage({type:'error', payload: String(err), id}, '*');
      }
    });
  })();
</script>
</body></html>`;

  iframe.srcdoc = html;

  const postCode = (code, id = Date.now()) => {
    iframe.contentWindow.postMessage({code, id}, '*');
    return id;
  };

  return { postCode };
}

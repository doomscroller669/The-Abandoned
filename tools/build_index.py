"""Wrap the game page (the same file published as the Claude artifact) for the Android app.
Usage: python3 tools/build_index.py path/to/the-abandoned.html
Writes app/src/main/assets/index.html with a full page skeleton and the bundled three.js."""
import sys, pathlib
src = pathlib.Path(sys.argv[1]).read_text()
cdn = 'https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js'
assert cdn in src, 'three.js script tag not found'
src = src.replace(cdn, 'three.min.js')
head = ('<!doctype html><html><head><meta charset="utf-8">'
        '<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">'
        '<style>:root{box-sizing:border-box}body{margin:0}[hidden]:not([hidden=until-found i]){display:none!important}</style>'
        '</head><body>')
out = pathlib.Path(__file__).resolve().parent.parent / 'app/src/main/assets/index.html'
out.write_text(head + src + '</body></html>')
print('wrote', out, out.stat().st_size, 'bytes')

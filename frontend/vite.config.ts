import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
// @ts-ignore
import path, { resolve } from 'path';
import fixReactVirtualized from 'esbuild-plugin-react-virtualized';
import { viteStaticCopy } from 'vite-plugin-static-copy';

export default defineConfig(({ mode }) => ({
  plugins: [
    react(),
    viteStaticCopy({
      targets: [
        {
          src: 'src/assets/**/*',
          dest: 'assets',
        },
      ],
    }),
  ],
  base: mode === 'wsl' || mode === 'aws' ? '/dist/' : '/',
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
  },
  define: { global: 'window' },
  server: {
    proxy: {
      ['/api']: {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
    port: 5173,
  },
  optimizeDeps: {
    esbuildOptions: {
      plugins: [fixReactVirtualized],
    },
  },
  build: {
    assetsDir: 'assets',
    rollupOptions: {
      output: {
        assetFileNames: 'assets/[name].[hash][extname]',
      },
    },
    outDir: path.resolve(__dirname, '../src/main/resources/static/dist'),
  },
}));

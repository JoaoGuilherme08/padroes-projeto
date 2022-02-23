FROM node:12.22.7-alpine  as node

WORKDIR /app

COPY package*.json ./
COPY src ./src/
COPY images ./images/
COPY babel.config.js ./
COPY tailwind.config.js ./
COPY postcss.config.js ./
COPY public ./public/
COPY nginx.conf ./

RUN npm install
RUN npm run build --mode=production

FROM nginx:stable-alpine as production-stage
 
COPY --from=node /app/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist /usr/share/nginx/html

RUN ls /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
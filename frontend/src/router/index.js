import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/invites',
      component: () => import('../components/ui/InviteGrid.vue'),
    },
    {
      path: '/projects',
      component: () => import('../components/ui/ProjectGrid.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/posts',
      component: () => import('../components/ui/PostGrid.vue'),
    },
    {
      path: '/attachments',
      component: () => import('../components/ui/AttachmentGrid.vue'),
    },
    {
      path: '/comments',
      component: () => import('../components/ui/CommentGrid.vue'),
    },
  ],
})

export default router;

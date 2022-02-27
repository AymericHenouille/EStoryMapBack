-- Insert users
insert into users (idu, mail) values (0, 'aymeric.defossez@chillycheesy.com');
insert into users (idu, mail) values (1, 'aymeric.henouille@chillycheesy.com');

-- Insert workspaces
insert into workspace (label, color, emoji, owner_id) values ('Workspace 1', '#000000', 'a', 0);
insert into workspace (label, color, emoji, owner_id) values ('Workspace 2', '#FFFFFF', 'a', 0);
insert into workspace (label, color, emoji, owner_id) values ('Workspace 1', '#000000', 'a', 1);
insert into workspace (label, color, emoji, owner_id) values ('Workspace 2', '#FFFFFF', 'a', 1);

-- share workspace to user
insert into workspaces (user_id, workspace_id) values (0, 3);
insert into workspaces (user_id, workspace_id) values (1, 1);
<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/documentation/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** Database username */
define( 'DB_USER', 'root' );

/** Database password */
define( 'DB_PASSWORD', '' );

/** Database hostname */
define( 'DB_HOST', 'localhost' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'Eyw.ie4[FP6cNxwB2jR`e4u8HTJt}/p#p&QHXL-0}&h7eQ/tIQqIP/Y35331|*$B' );
define( 'SECURE_AUTH_KEY',  ':}?zv]O=&bL+Tf~GIe7U|YpL7?p[{a5nm-wm!7-HFV%>f,e!3yes!^%[NA[bwlC,' );
define( 'LOGGED_IN_KEY',    'C.#-zTOaZlya7u|S;hX@#=jIf`~W@WwIqx{IyNavQK8S1.f e5gI.URx-]NNC]]S' );
define( 'NONCE_KEY',        'Z<c!1*|_{|b5+GCj!=v;EDbmc&):np*setp2xB{RTT*h~1$`XR1/8%%{Ca`H7CEL' );
define( 'AUTH_SALT',        '2 ]x(<7N:* !Go.QC+#^$0qyK<2Mq$AYM*an9{N.6/DO$Ig2cnSPO.MoWn:W!= R' );
define( 'SECURE_AUTH_SALT', 'ELpH*/~`$o08KD3}TA0#:R@~#/lVv4w-o*[fd6sK14uAPD#jBTk,_.*ekD8/H5[d' );
define( 'LOGGED_IN_SALT',   '?v7}Y%;}4T?j~IG!hCYOjOo7w^k=)  {56zViJ}vI^+c=w0{.g8EaQ%migzi,r$u' );
define( 'NONCE_SALT',       'j[>>L>77LtMq%l@] 3/VBQBGX/B$nL4G`5Pw]#<Kd_`9}(c8T7UuuvHG>dJ~<g<A' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/documentation/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
